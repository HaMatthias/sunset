<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# sunset Changelog

## [Unreleased]

### Added

- Plugin Action: Swap Theme -> Swaps the theme via action to the other theme set in the settings.

### Changed

- Allow up to 19 digits for longitude/latitude in the settings. (+1 dot character = 20 characters)

## [0.0.4] - 2025-08-03

### Added

- Added tooltip for the random strategy in the settings page.
- Allow to back up and sync the settings.
- Added updates from IntelliJ Platform Plugin Template repository.
- Small info comment for the solar event strategy showing the next solar events.
- Added info notification which is displayed one minute before the theme is changed.

### Fixed

- Immediate theme change when installing the plugin. Instead, the current installed theme is set as default.
- Set a default theme as string if no theme is installed.

### Changed

- Rename the location strategy to solar event strategy.

## [0.0.3] - 2025-07-22

### Added

- Initial scaffold created from [IntelliJ Platform Plugin Template](https://github.com/JetBrains/intellij-platform-plugin-template)
- Add theme changing strategies

[Unreleased]: https://github.com/HaMatthias/sunset/compare/v0.0.4...HEAD
[0.0.4]: https://github.com/HaMatthias/sunset/compare/v0.0.3...v0.0.4
[0.0.3]: https://github.com/HaMatthias/sunset/commits/v0.0.3
