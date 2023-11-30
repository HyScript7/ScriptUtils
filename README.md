<a name="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/HyScript7/ScriptUtils">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">ScriptUtils</h3>

  <p align="center">
    A multi-purpose discord bot written in Java using Spring and JDA. It aims to provide a unique version control system and many other modules you'll be familiar with.
    <br />
    <a href="https://discord.com/api/oauth2/authorize?client_id=933826955172782091&permissions=8&scope=bot"><strong>Invite »</strong></a>
    <br />
    <br />
    <a href="https://discord.gg/DvJxHDsw78">Join Discord</a>
    ·
    <a href="https://github.com/HyScript7/ScriptUtils/wiki">Read the Docs</a>
    .
    <a href="https://github.com/HyScript7/ScriptUtils/issues">Report Bug</a>
    ·
    <a href="https://github.com/HyScript7/ScriptUtils/issues">Request Feature</a>
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

This is the repository for ScriptUtils. A multi-purpose discord bot with the goal of making multi-server administration simpler.

The main goal of this project is to bring databases and version control to discord servers. See the <a href="#roadmap">roadmap</a> and <a href="https://github.com/HyScript7/ScriptUtils/issues">issues</a> for a full list of proposed features and current issues!

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Built With

- [![Java][java-shield]][java-url]
- [![Gradle][gradle-shield]][gradle-url]
- [![Spring][spring-shield]][spring-url]
- [![JDA][jda-shield]][jda-url]

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->

## Getting Started

For production envrionment open `docker-compose.yml`, change environment variables to your need and then run `docker-compose up -d`. This will setup scriptutils as it's intended to be without the trouble of managing dependencies your self.

For development purposes, follow the guide below.

### Prerequisites

You need to have JRE or JDK >=17 installed. If you don't, go to [Eclipse Adoptium](https://adoptium.net/) and download the version you require.
I also recommend you have a local Gradle installation.

### Installation

1. Clone the repo
   ```sh
   git clone https://github.com/HyScript7/ScriptUtils.git
   ```
2. Build using gradle
   ```sh
   ./gradlew build bootJar
   ```
3. You can now run the bot using
   ```
   java -jar ./build/libs/scriptutils-*-SNAPSHOT.jar
   ```
4. Configure the bot token in application.properties
   ```
   bot.token=your.token.here
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->

## Usage

This section will contain information on how to configure scriptutils for self hosting.

_For more examples, please refer to the <a href="https://github.com/HyScript7/ScriptUtils/wiki">Wiki</a>_

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ROADMAP -->

## Roadmap

- [ ] Moderation: Provides moderation commands that integrate well with the logging module
- [ ] Auto Mod: Configurable auto moderation
- [ ] Anti Raid: Attempts to stop raids before they occur
- [ ] Economy: Simulates a virtual currency and item or resource based marked
- [ ] Activity Analytics: Allows you to create a graph of server growth or channel activity
- [ ] Tickets: A simple-to-use ticket solution for your server
  - [ ] Mod Mail: It's like tickets, but without creating any extra channels!
- [ ] Logging: A fancy logging solution for your server
- [ ] Developer: Commands that let you create a server without having to navigate tons of menus
- [ ] Version Control: Allows you to version changes made to your server and create snapshots (backups)
- [ ] Network Sync: Allows you to sync roles, bans, updates, etc... across two or more servers
- [ ] Database: _Implementation unclear_
- [ ] Whitelist: Allows you to restrict access to a server (inverse banlist)

See the [open issues](https://github.com/HyScript7/ScriptUtils/issues) for a full list of proposed features (and known issues).

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- LICENSE -->

## License

Distributed under the MIT License. See the `LICENSE` file for more information.

<div><i>ScriptUtils Logo CC-BY <a href="https://github.com/mobilex1122">mobilex1122</a></i></div>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->

## Contact

HyScript7 - Discord: @hyscript7 - Email: hyscript7@gmail.com

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- ACKNOWLEDGMENTS -->

## Acknowledgments

- [Readme Template](https://github.com/othneildrew/Best-README-Template)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->

[contributors-shield]: https://img.shields.io/github/contributors/github_username/repo_name.svg?style=for-the-badge
[contributors-url]: https://github.com/github_username/repo_name/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/github_username/repo_name.svg?style=for-the-badge
[forks-url]: https://github.com/github_username/repo_name/network/members
[stars-shield]: https://img.shields.io/github/stars/github_username/repo_name.svg?style=for-the-badge
[stars-url]: https://github.com/github_username/repo_name/stargazers
[issues-shield]: https://img.shields.io/github/issues/github_username/repo_name.svg?style=for-the-badge
[issues-url]: https://github.com/github_username/repo_name/issues
[license-shield]: https://img.shields.io/github/license/github_username/repo_name.svg?style=for-the-badge
[license-url]: https://github.com/github_username/repo_name/blob/master/LICENSE.txt
[product-screenshot]: images/screenshot.png
[java-shield]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[gradle-shield]: https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white
[spring-shield]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[jda-shield]: https://img.shields.io/badge/JDA-%235F00B9.svg?style=for-the-badge&logo=discord&logoColor=white
[java-url]: https://www.java.com/en/
[gradle-url]: https://gradle.org/
[spring-url]: https://spring.io/
[jda-url]: https://jda.wiki/
