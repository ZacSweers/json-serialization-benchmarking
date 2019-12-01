#!/bin/bash

# The website is built using MkDocs with the Material theme.
# https://squidfunk.github.io/mkdocs-material/
# It requires Python to run.
# Install the packages with the following command:
# pip install mkdocs mkdocs-material

# To run, just execute this. ./deploy_website.sh

# To install locally, run ./deploy_website.sh --local

if [ "$1" = "--local" ]; then local=true; fi
if ! [ $local ]; then
  set -ex

  REPO="git@github.com:ZacSweers/json-serialization-benchmarking.git"
  DIR=temp-clone

  # Delete any existing temporary website clone
  rm -rf $DIR

  # Clone the current repo into temp folder
  git clone $REPO $DIR

  # Move working directory into temp folder
  cd $DIR
fi

# Copy in special files that GitHub wants in the project root.
cp CHANGELOG.md docs/changelog.md
cp CONTRIBUTING.md docs/contributing.md

# Build the site and push the new files up to GitHub
if ! [ $local ]; then
  mkdocs gh-deploy
else
  mkdocs serve
fi

# Delete our temp folder
if ! [ $local ]; then
  cd ..
  rm -rf $DIR
fi