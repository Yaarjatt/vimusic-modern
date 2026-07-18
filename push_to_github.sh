#!/bin/bash
# Push script for modernized ViMusic clone
# Usage: GITHUB_TOKEN=<token> GITHUB_REPO=<repo-url> ./push_to_github.sh

REPO_URL="${GITHUB_REPO:-https://github.com/user/vimusic-modern}"
TOKEN="${GITHUB_TOKEN:-}"

if [ -z "$TOKEN" ]; then
    echo "Error: GITHUB_TOKEN not set. Export it securely: export GITHUB_TOKEN=<token>"
    exit 1
fi

echo "Setting remote: $REPO_URL"
git remote add origin "https://x-access-token:${TOKEN}@github.com/${REPO_URL#https://github.com/}" 2>/dev/null || git remote set-url origin "https://x-access-token:${TOKEN}@github.com/${REPO_URL#https://github.com/}"

echo "Pushing branch: $(git rev-parse --abbrev-ref HEAD)"
git push -u origin $(git rev-parse --abbrev-ref HEAD) --force-with-lease
