package com.hmusic.new.ui.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveableStateHolder
import androidx.compose.ui.platform.LocalContext
import com.hmusic.new.compose.persist.PersistMapCleanup
import com.hmusic.new.compose.routing.RouteHandler
import com.hmusic.new.compose.routing.defaultStacking
import com.hmusic.new.compose.routing.defaultStill
import com.hmusic.new.compose.routing.defaultUnstacking
import com.hmusic.new.compose.routing.isStacking
import com.hmusic.new.compose.routing.isUnknown
import com.hmusic.new.compose.routing.isUnstacking
import com.hmusic.new.Database
import com.hmusic.new.R
import com.hmusic.new.models.SearchQuery
import com.hmusic.new.query
import com.hmusic.new.ui.components.themed.Scaffold
import com.hmusic.new.ui.screens.albumRoute
import com.hmusic.new.ui.screens.artistRoute
import com.hmusic.new.ui.screens.builtInPlaylistRoute
import com.hmusic.new.ui.screens.builtinplaylist.BuiltInPlaylistScreen
import com.hmusic.new.ui.screens.globalRoutes
import com.hmusic.new.ui.screens.localPlaylistRoute
import com.hmusic.new.ui.screens.localplaylist.LocalPlaylistScreen
import com.hmusic.new.ui.screens.playlistRoute
import com.hmusic.new.ui.screens.search.SearchScreen
import com.hmusic.new.ui.screens.searchResultRoute
import com.hmusic.new.ui.screens.searchRoute
import com.hmusic.new.ui.screens.searchresult.SearchResultScreen
import com.hmusic.new.ui.screens.settings.SettingsScreen
import com.hmusic.new.ui.screens.settingsRoute
import com.hmusic.new.utils.homeScreenTabIndexKey
import com.hmusic.new.utils.pauseSearchHistoryKey
import com.hmusic.new.utils.preferences
import com.hmusic.new.utils.rememberPreference

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun HomeScreen(onPlaylistUrl: (String) -> Unit) {
    val saveableStateHolder = rememberSaveableStateHolder()

    PersistMapCleanup("home/")

    RouteHandler(
        listenToGlobalEmitter = true,
        transitionSpec = {
            when {
                isStacking -> defaultStacking
                isUnstacking -> defaultUnstacking
                isUnknown -> when {
                    initialState.route == searchRoute && targetState.route == searchResultRoute -> defaultStacking
                    initialState.route == searchResultRoute && targetState.route == searchRoute -> defaultUnstacking
                    else -> defaultStill
                }

                else -> defaultStill
            }
        }
    ) {
        globalRoutes()

        settingsRoute {
            SettingsScreen()
        }

        localPlaylistRoute { playlistId ->
            LocalPlaylistScreen(
                playlistId = playlistId ?: error("playlistId cannot be null")
            )
        }

        builtInPlaylistRoute { builtInPlaylist ->
            BuiltInPlaylistScreen(
                builtInPlaylist = builtInPlaylist
            )
        }

        searchResultRoute { query ->
            SearchResultScreen(
                query = query,
                onSearchAgain = {
                    searchRoute(query)
                }
            )
        }

        searchRoute { initialTextInput ->
            val context = LocalContext.current

            SearchScreen(
                initialTextInput = initialTextInput,
                onSearch = { query ->
                    pop()
                    searchResultRoute(query)

                    if (!context.preferences.getBoolean(pauseSearchHistoryKey, false)) {
                        query {
                            Database.insert(SearchQuery(query = query))
                        }
                    }
                },
                onViewPlaylist = onPlaylistUrl
            )
        }

        host {
            val (tabIndex, onTabChanged) = rememberPreference(
                homeScreenTabIndexKey,
                defaultValue = 0
            )

            Scaffold(
                topIconButtonId = R.drawable.equalizer,
                onTopIconButtonClick = { settingsRoute() },
                tabIndex = tabIndex,
                onTabChanged = onTabChanged,
                tabColumnContent = { Item ->
                    Item(0, "Quick picks", R.drawable.sparkles)
                    Item(1, "Songs", R.drawable.musical_notes)
                    Item(2, "Playlists", R.drawable.playlist)
                    Item(3, "Artists", R.drawable.person)
                    Item(4, "Albums", R.drawable.disc)
                }
            ) { currentTabIndex ->
                saveableStateHolder.SaveableStateProvider(key = currentTabIndex) {
                    when (currentTabIndex) {
                        0 -> QuickPicks(
                            onAlbumClick = { albumRoute(it) },
                            onArtistClick = { artistRoute(it) },
                            onPlaylistClick = { playlistRoute(it) },
                            onSearchClick = { searchRoute("") }
                        )

                        1 -> HomeSongs(
                            onSearchClick = { searchRoute("") }
                        )

                        2 -> HomePlaylists(
                            onBuiltInPlaylist = { builtInPlaylistRoute(it) },
                            onPlaylistClick = { localPlaylistRoute(it.id) },
                            onSearchClick = { searchRoute("") }
                        )

                        3 -> HomeArtistList(
                            onArtistClick = { artistRoute(it.id) },
                            onSearchClick = { searchRoute("") }
                        )

                        4 -> HomeAlbums(
                            onAlbumClick = { albumRoute(it.id) },
                            onSearchClick = { searchRoute("") }
                        )
                    }
                }
            }
        }
    }
}
