### Alpas - The Rapid and Delightful Kotlin Web Framework. Easy, elegant, and productive! 🚀

> This app is built by following [Let's build a web app from scratch to finish with Alpas + Kotlin][devto-tutorial] tutorial.

Alpas is a batteries-included web framework for Kotlin with a strong focus on developers' productivity.
The main goal of Alpas is to get you started quick and enable you to move faster while
letting you enjoy doing what you are the best at — crafting a delightful web app.

Alpas strives to be simple and elegant and wants to serve you whether you have written any JVM based web
apps before or not—there is no xml or properties file to configure, no scattered annotations
to memorize—and yet, there is no huge learning curve to get started.

Alpas comes bundled with most of what you need to write modern web apps—**authentication**,
**auth scaffolding**, **email verification**, **notifications**, **mail**, **queues**,
**fast and intuitive routing**, **powerful templating engine** etc.—and yet it
remains flexible for you to extend it to make it more powerful and
delightful than it already is.

We have sweated picking the good parts, so you don’t have to!

### Pre-requisites

Please visit [Alpas installation documentation][alpas-setup] for a full list of system requirements
and pre-requisites. Here is a quick list of what you need for Fireplace:

* *nix machine. Windows is supported but only under the WSL or using GitBash.
* JDK version >= 9.0
* Gradle >= 5.6
* IntelliJ IDEA Community Editor or Ultimate.
* A MySQL database. 5.7 is recommended but 8.0 should work just as fine.
* NodeJS and Yarn for building assets.

### Running Fireplace on your local machine

1. Make sure you have all the [system requirements needed by Alpas](https://alpas.dev/docs/installation).
2. Fork this repo and clone it on your machine.
3. Make a copy of `.env.example` and name it `.env`.
4. Make sure you have a MySQL database named `fireplace`. You can tweak database settings in your `.env` file.
5. The root of the project contains an `alpas` script. Make it executable: `chmod +x ./alpas`
6. Build and serve the project: `./alpas build && ./alpas serve` 


[happy-kotlin]: https://medium.com/signal-v-noise/kotlin-makes-me-a-happier-better-programmer-1fc668724563
[alpas-slack]: https://join.slack.com/t/alpasdev/shared_invite/enQtODcwMjE1MzMxODQ3LTJjZWMzOWE5MzBlYzIzMWQ2MTcxN2M2YjU3MTQ5ZDE4NjBmYjY1YTljOGIwYmJmYWFlYjc4YTcwMDFmZDIzNDE
[alpas-docs]: https://alpas.dev/docs
[alpas-setup]: https://alpas.dev/docs/installation
[devto-tutorial]: https://dev.to/ashokgelal/let-s-build-a-web-app-from-scratch-to-finish-with-alpas-and-kotlin-29eo
