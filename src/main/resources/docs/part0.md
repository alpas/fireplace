# Let's build a web app from scratch to finish with Alpas and Kotlin

[Alpas](https://alpas.dev/) is a new web framework for developers who want to have a rapid, yet delightful, web application development experience using Kotlin.

[Kotlin](https://kotlinlang.org/) is a general-purpose programming language with concise yet readable syntax. Its popularity is [exploding](https://explodingtopics.com/topic/kotlin) making it one of the [fastest-growing languages](https://octoverse.github.com/) according to GitHub. It's one of the [most loved languages](https://insights.stackoverflow.com/survey/2019#most-loved-dreaded-and-wanted) according to Stack Overflow, and [used by companies big and small](https://www.businessinsider.com/kotlin-programming-language-explained-popularity-2019-5).

Kotlin is modern, fun, powerful, and delightful and so is Alpas. Kotlin [makes you happy writing code](https://medium.com/signal-v-noise/kotlin-makes-me-a-happier-better-programmer-1fc668724563), and Alpas strives to make you happy writing a web app.

Kotlin's strong typing means you get to experience all the greatness of a statically typed language - great IDE support for code navigation, powerful debugging, fearless refactoring, and bugs that get caught at the compile-time and not at the time when your users are doing something critical.

Kotlin's root in JVM and full interoperability with Java means you get to be a part of, arguably, the largest and the richest programming ecosystem of any language.

---

Don't let the use of a statically typed language and the mention of JVM and Java fool you! I wouldn't blame you if you have tried some other Kotlin/JVM/Java frameworks and are burnt and defeated either by their sheer complexity because they are too big, or the lack of a coherent ecosystem because they are too small.

Professionally and personally, I've experienced both kinds - one where a framework is so complex that getting started takes hours if not days, and the documentation is a maze full of technical jargons with no exit in sight.

The worst is that they assume that you are already familiar with the Java ecosystem - beans, annotations, auto-wirings, thousands of lines of XML, properties, servlets, and factories, etc. They have a framework for a framework and a project generator!

![Me trying to use an existing Java web framework.](https://media.giphy.com/media/AANqYGD9LVsw8/giphy.gif)


> **You may now have a fancy new weapon with Kotlin but you are still playing the same old game with too many rules and institutional knowledge. Well, that sucks! Why can't we have a new complete game designed just for the new weapon?**

On the other side of the tunnel, things are even worse. In the name of being light and micro, they let you get started but then leave you hanging when it comes to, say, templating, or database integration, or queues, or notifications, or sending emails, etc.

They hand wave and tell you to go figure it out yourselves. Most of them would say "For this component, we don't want to make a choice for you. Here are 10 different libraries for getting this thing done; figure out the one you may want to use yourself."

You are now left with so many choices with no clue on which one to pick and even if you were brave enough to pick one; you don't know whether or not you made a good choice.

Making a decision is not only hard but is mentally taxing. It forces you to make irrational trade-offs. If you are new to the whole web development thing, then good luck with making a good choice when you are just trying to understand the basics.

A motivated soul just wants to get a web project done, but now the poor soul is wandering in the hell of a rabbit hole. No wonder they end up using a subpar language because, well, people need to get shit done.

---

Alpas avoids both extremes by including sane defaults and picking and packing the good stuff for you so you can get started and get going for the actual thing you are after - crafting a delightful app for your clients.

It takes its inspirations from other delightful web frameworks like Laravel, Vapor, Rails, etc.

**In fact, if you have used the Laravel framework before, you will feel right at home with Alpas.** The core members of the framework have worked with Laravel for many years and wanted to bring a similar experience to Kotlin/Java world.

I will not lie - Alpas still has [some way to go](https://github.com/alpas/alpas/issues/6) before we have everything, but what it already has is good enough for building a complete web app. We have already used Alpas in writing 4 complete web apps that are in production right now.

I could go on and on about [Alpas](https://alpas.dev/). But instead of just praising it, let's put it out for a real show for you to see and judge it yourself.
In the next several blog posts we will use Alpas to write a complete web app from scratch to finish, which will demo some of its simplicity, delights, and power.

### 🔥 Building Fireplace

The app we'll be building is a small but nonetheless a complete web app for managing projects. You can create an account, enforce users to verify their emails, add projects, add some tasks, and invite collaborators.
I wanted to keep it small but also wanted to show some out-of-the-box features of Alpas - [auth scaffolding](https://alpas.dev/docs/authentication-scaffolding), [templating](https://alpas.dev/docs/pebble-templates), [notifications](https://alpas.dev/docs/notifications), [migrations](https://alpas.dev/docs/migrations), [routing](https://alpas.dev/docs/routing), [mails](https://alpas.dev/docs/mail), etc.

If you want to play with the app first before you jump in, it's available at https://fireplace.alpas.dev

If you want to read the source code, the complete [source code is available on GitHub](https://github.com/alpas/fireplace). We are already working on screencasts of this whole series and should be out soon. [Follow Alpas on Twitter](https://twitter.com/alpasdev) to know when it is out.

In the next post, we'll see how easy it is to get started with Alpas and how stupidly easy it is to scaffold an auth system with a single [Alpas console command](https://alpas.dev/docs/alpas-console).

I hope you will join me in the journey of this super fun exercise of writing a complete web app. I promise it will be fun and I'm confident that you will love the fun. After all, who doesn't love creating something from scratch for fun? We wouldn't call ourselves programmers if we didn't. Right?

See you in the next one!
