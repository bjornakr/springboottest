Hi!

If you want to manage the configuration, first you have to think about if you
should put the changes in source control or not.

* Use `resources/config/application.properties` for local-only configuration.
    You need to create this directory itself if it doesn't already exist.
    It is already listed in .gitignore. Make sure it is ignored by running `git status`.
    Local config is good for:
  * Changing logging levels
  * ...
* Use `resources/application.properties` for changes that will be added to source control.
  Use this for:
  * Any changes that all the developers need to use. 

Both configs are used, but the local config will add to and override the settings in 
 