# AIA-Part2

## Project
Project should be fixed now. Steps : 

* Close project in IntelliJ
* Import New Project from file
* Navigate to the folder
* Select the iml file (need to check with matty how this was done)
* Working project should now exist.

## Running

* Run the Repl
* (load "imports")
* (startup 2222)
* On Netlogo click the connect button
* On Netlogo click the start-repl button
* (map nlogo-send (map nlogo-translate-cmd (get (ops-search world (quote ((escaped prisoner true))) operations-prisoner) :cmds)))

### an error will appear at this point.
* all the commands however make it through. This is checkable by using flush-io instead of start-repl
