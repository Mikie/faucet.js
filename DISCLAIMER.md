We (the faucet.js development team) don't expect nor recommend you build an entire server or network off of a JavaScript Interpreter: it will have serious ramifications on the performance of the server. However, if in fact, you do decide to use one do not be surprised if the performance of the server suffers. Faucet.js like all JavaScript interpreters built in Java will run a JavaScript VM to execute all the functions of a script. Spigot servers are primarily single threaded, therefore will consume a fair amount of processing power to execute functions within the script which in turn will starve other plugins written in Java alongside the server software itself from using those resources.

Of course, we, as core contributors, will do our best to optimise this plugin to the best of our ability. However, we advise against using this to purely build an entire server or network.

Faucet.js is a brilliant tool to use if you are wanting to dive into programming or add small custom features to your server.

**The faucet.js development team is not held responsible for any damage caused to your server through scripts or modules.**
