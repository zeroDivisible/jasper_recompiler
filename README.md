=================
## Info

This is quick and dirty app - was created in like 10-20 minutes. Don't expect code quality here.

On my machine, iReport designer was failing to recompile .jrxml files, even when the versions of it and the jasper dependency were matched. I didn't want to find solution for that one online as error messages were cryptic, so this simple tool makes it a bit easier.

## Usage
Just change the dependency for jasper reports in the gradle file into proper one, run ``gradle fatJar`` and produced jar can be used like this:

```
java -jar jasper-reports.jar /path/to/dir/containing/jrxml
```
