# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this repository is

The **Flexberry PLATFORM documentation website** — a static site built with [Jekyll](https://jekyllrb.com/) (GitHub Pages compatible) that publishes the technical documentation for the Flexberry platform family (Flexberry ORM, Designer, ASP.NET, Ember addons, GIS, etc.).

The bulk of the work here is writing and organizing **Markdown documentation**, not application code. "Development" = editing doc pages, building/running the site, and maintaining sidebar navigation. The theme is a custom variant of Tom Johnson's `documentation-theme-jekyll`.

## Building and running the site

- **Run locally (recommended):** `bundle exec jekyll serve` → visit http://localhost:4000
- **Run via Docker (compose, preferred):** from the repo root, `docker compose -f docker/docker-compose.yml up` → visit http://localhost:4000. Serves with `--livereload --incremental --force_polling` (polling so file changes on Windows/Docker bind-mounts are picked up) and caches installed gems in the `jekyll-bundle` named volume across restarts. Down with `docker compose -f docker/docker-compose.yml down` (stop with `down -v` to also clear the bundle cache).
- **Run via Docker (legacy one-liner):** `.\run-jekyll.cmd` — mounts the repo into `jekyll/jekyll:3.8.3` and serves on port 4000.
- **Build (CI / production):** `bundle exec jekyll build`
- Dependencies: `gem 'github-pages'` and `jekyll-seo-tag` in [Gemfile](Gemfile)

There are no unit tests. The `.travis.yml` CI only runs `bundle exec jekyll build`. **Never commit the generated `_site/` directory** — it is git-ignored.

## Content architecture

### Documentation pages live in `pages/`

- `pages/guides/` — courses/tutorials, split into `base-tech/`, `tasks/`, `practical-guides/`, `mobile-app/`, `flexberry-base/`, etc.
- `pages/products/` — per-product docs (one subdir per product, e.g. `flexberry-orm`, `flexberry-gis`, `ember-flexberry-gis`).
- `pages/news/`, `pages/tags/`, `pages/trainings/` — the rest of the site.

**Bilingual scheme.** Each article exists as a pair of files: `<slug>.ru.md` and `<slug>.en.md`. Both carry `lang: ru` / `lang: en` and a `permalink` in frontmatter, e.g. `permalink: ru/gbt_aspnet.html`. Keep both language versions in sync when editing (or at least be aware of the sibling).

### Page frontmatter conventions

Every page starts with a YAML frontmatter block. Common fields:

```yaml
---
title: ASP.NET
keywords: Programming
sidebar: guide-base-tech_sidebar   # which sidebar TOC this page appears in
toc: true | false                   # show in-page table of contents
permalink: ru/gbt_aspnet.html       # custom URL (note ru/ or en/ segment)
lang: ru | en
summary: Short description shown under the title.
---
```

### Navigation is driven by `_data/sidebars/*.yml`

Sidebars are NOT configured per-page in code — they are YAML data files ([_data/sidebars/](_data/sidebars/)) one per section, e.g. `flexberry-orm_sidebar.yml`, `guide-base-tech_sidebar.yml`, `home_sidebar.yml`. Each entry has a `title` and `url` pointing at a generated page; entries may carry `title_ru` (Russian label), `output: web, pdf`, and nested `folderitems`. The full sidebar list is registered in [`_config.yml`](_config.yml) under `sidebars:` — **a new sidebar must be added there AND a matching YAML file created.**

### Cross-page links

References inside pages are plain relative filenames (e.g. `gbt_csharp.html`) or root-relative `/ru/...` paths — not Jekyll Liquid `{% link %}` tags. Match the existing style in the file you edit.

### Other content mechanisms

- `_posts/` — blog posts.
- `_tooltips/` — glossary/tooltip collection (`output: false`, rendered inline, not as standalone files).
- `_data/` — besides `sidebars/`, holds `topnav.yml`, `glossary.yml`, `tags.yml` (the `allowed-tags` list), `alerts.yml`, `definitions.yml`, `strings.yml`.
- Custom includes in `_includes/custom/` (e.g. `getting_started_series.html`) provide series/map navigation used via `simple_map`/`complex_map` frontmatter.

## Theming

- `_layouts/` — page layouts (`default.html` wraps `page.html`/`post.html`).
- `_includes/` — partials (topnav, sidebar, toc, callouts like `note.html`/`tip.html`/`warning.html`/`important.html`).
- `_sass/`, `css/`, `js/` — styling (Bootstrap + `theme-flexberry.css`, `customstyles.css`) and static scripts.
- `index.ru.html` / `index.en.html` — the hand-authored landing pages for each language.

## Markdown linting

A [`.markdownlint.json`](.markdownlint.json) config exists. It explicitly **disables** MD002 (first-heading-h1), MD013 (line-length), MD024 (duplicate-headings), and MD033 (inline-html) — content legitimately violates these (long lines, raw HTML blocks, repeated headings). Do not re-enable them globally.

## Rules, notes and gotchas

- `.gitmodules` is present but empty; clone with `git clone --recursive` is unnecessary in practice.
- The GitHub Actions workflow ([.github/workflows/translate.yml](.github/workflows/translate.yml)) runs the `NewPlatform.Flexberry.DocTranslate` tool (mono) to auto-translate pages between languages and commits the result as "Autotranslated". It depends on org secrets. Don't rely on it being runnable locally.
- Always respond to the user in Russian.
