# A web app from scratch to finish with Alpas and Kotlin — Up and Running

If you are reading this then maybe [I’ve convinced you](https://dev.to/ashokgelal/let-s-build-a-web-app-from-scratch-to-finish-with-alpas-and-kotlin-29eo) to try out both Kotlin and [Alpas](https://alpas.dev) by writing a complete web app. I’m super happy you are here and I’ll try my best to make sure your time is worth it and well spent. Let’s get started!

We will keep this post short and simple. We are just going to create a new project from a template, talk about few things that you need to know right off the bat, and build and run it.

Get started by first [making sure your system is ready](https://alpas.dev/docs/installation#system-requirements) for writing a Kotlin web app using Alpas.

Now, visit [Alpas starter template repo](https://github.com/alpas/starter) and create a new repo based on this template (click that green **Use this template**  button).

1. Name your repo fireplace and clone it on your local machine.
2. At the root of the project there is a script named _alpas_. Make it executable: `chmod +x ./alpas`
3. Initialize your new project using the full package name: `./alpas init com.example.fireplace`
4. Once done, to [serve your app](https://alpas.dev/docs/installation#serving-locally), do: `./alpas serve`
5. Just to make sure that your frontend dependencies are not outdated, run `yarn install && yarn dev` (make sure that you have NodeJS and npm installed).
6. Download the excellent [IntelliJ IDEA](https://www.jetbrains.com/idea/) and open the project.

There isn’t much to do for this part. But once you open the project, browse the app to try to get an idea of [the structure of an Alpas web app](https://alpas.dev/docs/project-structure). This will help you to navigate faster when we start adding more features in future.

To help you get oriented, here are are some of the files you can skim over:

- `start.kt`

The is the file where it all starts. You probably never have to touch this file except for actually running the app. IntelliJ should have a “green play button” right next to the `main` function that you can click to run your app.

- `.env`

This contains all your environment related configuration values. You can override so many of the core configuration values in this file.

Start by changing the value of APP\_NAME variable and see what happens when you run your app again. Can you change the port address at which your app is served by changing one of the values here? Which one?

Do a quick read on [_Configuration_](https://alpas.dev/docs/configuration) documentation to better understand one of the most critical components of Alpas.

- `configs/DatabaseConfig.kt`

This class file is where you’d be adding database connections for running CRUD operations. I highly recommend going over [_Database Getting Started_](https://alpas.dev/docs/database-getting-started) documentation to prepare for the next part.

- `routes.kt`

All your app routes should be kept in this file. You can read more about Alpas [Routing](https://alpas.dev/docs/routing) to be better prepared as we’ll be adding bunch of them here very soon.

- `controllers/WelcomeController.kt`

The controller responsible for deciding which page gets rendered when the home page route is matched. Alpas [_Controllers_](https://alpas.dev/docs/controllers) documentation is a quick read.

- `resources/templates/welcome.peb`

The “landing” page of the app. Feel free to tweak it if you want to but don’t remove anything yet. You can change the title of the page if you want. Being familiar with Alpas [_Templating_](https://alpas.dev/docs/pebble-templates) engine is going to help you move much faster.

We did not write anything in this post but only created a project and that was intentional. I want you to spend some time reading [Alpas docs](https://alpas.dev/docs).

Don’t worry, you don’t need to know everything; just being familiar with where to look for things is more than enough at this time.

If you want to check out what the project looks like at this point, the [source code of Fireplace is available on GitHub](https://github.com/alpas/fireplace/releases/tag/v0.1.0) and is tagged as [_v0.1.0_](https://github.com/alpas/fireplace/releases/tag/v0.1.0).

If you found a bug please open an issue. If you have questions, comments, or feedback, or if you just want to hangout with people who are learning Alpas and with people who built Alpas, we have a [**dedicated Slack**](https://join.slack.com/t/alpasdev/shared_invite/enQtODcwMjE1MzMxODQ3LTJjZWMzOWE5MzBlYzIzMWQ2MTcxN2M2YjU3MTQ5ZDE4NjBmYjY1YTljOGIwYmJmYWFlYjc4YTcwMDFmZDIzNDE) for that. We’d love to have you there. [**Please join us**](https://join.slack.com/t/alpasdev/shared_invite/enQtODcwMjE1MzMxODQ3LTJjZWMzOWE5MzBlYzIzMWQ2MTcxN2M2YjU3MTQ5ZDE4NjBmYjY1YTljOGIwYmJmYWFlYjc4YTcwMDFmZDIzNDE) **!**

See you in the next one!

> **Questions?** [**Ask us in our Slack.**](https://join.slack.com/t/alpasdev/shared_invite/enQtODcwMjE1MzMxODQ3LTJjZWMzOWE5MzBlYzIzMWQ2MTcxN2M2YjU3MTQ5ZDE4NjBmYjY1YTljOGIwYmJmYWFlYjc4YTcwMDFmZDIzNDE)
