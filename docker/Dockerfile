# Flexberry PLATFORM documentation site — Jekyll build environment
#
# Deliberately minimal. The jekyll/jekyll image already ships with a complete,
# Ruby-2.5-compatible Jekyll + github-pages gemset, so there is nothing to
# pre-install at build time and the image builds reliably on a fresh clone
# (Gemfile.lock is git-ignored and may be absent). Installed gems are cached
# between restarts in the jekyll-bundle named volume (see docker-compose.yml).
# Build from the repository root so the source tree can be mounted at runtime:
#   docker compose -f docker/docker-compose.yml up
FROM jekyll/jekyll:3.8.3

# Default Jekyll working directory (matches the image's expected mount point).
WORKDIR /srv/jekyll

# Jekyll serve default port.
EXPOSE 4000

CMD ["jekyll", "serve"]
