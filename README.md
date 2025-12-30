# Sunset

![Downloads](https://img.shields.io/jetbrains/plugin/d/com.github.hamatthias.sunset?label=Downloads)
![Version](https://img.shields.io/jetbrains/plugin/v/com.github.hamatthias.sunset?label=Version)
![License](https://img.shields.io/github/license/hamatthias/sunset)

![Build](https://img.shields.io/github/actions/workflow/status/hamatthias/sunset/build.yml)

<!-- Plugin description -->
Adds the functionality to change between desired themes for one of the following strategies:
- **Solar Event**: Provide a location by specifying a latitude and longitude. The plugin will determine the sunrise and sunset times for that location and switch themes accordingly.
- **Time**: Specify a time range (e.g., 6 AM to 6 PM) for the theme switch. The plugin will change themes based on the current time of day.
- **Random**: Randomly select a theme off installed ones. This can be useful for applications that want to provide a different experience each time they are used.

Select two themes for switching. The plugin will automatically switch between the two themes based on the selected strategy.
<!-- Plugin description end -->

## Installation

1. Open IntelliJ IDEA.
2. Go to `File > Settings > Plugins`.
3. Search for "Sunset" in the JetBrains Marketplace.
4. Click `Install` and restart IntelliJ (If required).

## Usage

1. Open the settings under `File > Settings > Sunset`.
2. Select a strategy (Solar Event, Time, Random).
3. Configure the desired themes and parameters.
4. Save the settings and enjoy automatic theme switching!

## Examples

### Solar Event Strategy
- Set the coordinates for your location, and the plugin will automatically switch between themes based on sunrise and sunset.

### Time Strategy
- Choose a time range (e.g., 8 AM to 8 PM), and the plugin will switch themes accordingly.

### Random Strategy
- The plugin will randomly switch between all installed themes at specified intervals defined by the factor.

## Contributing

Contributions are welcome! Create an issue or a pull request on [GitHub](https://github.com/hamatthias/sunset).

## License

This project is licensed under the [MIT License](LICENSE).

## Contact

For questions or issues, reach out via [GitHub Issues](https://github.com/hamatthias/sunset/issues).