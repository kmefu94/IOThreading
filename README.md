# IOThreading
Takes one or more input and attempts to count the words while utilizing threading. Note: there is no GUI for this software, so in order to run it, the command should be Java -jar FileCount.jar

FileCount.jar is the compiled jar file with all the classes that are used in this program. 

Wordcount.java is where main is located and constructs the classes and objects initialized in counting.java

Counting.java has a run method which initializes a lock for the amount of files inputted; making sure that all the files are counted for before the counter lock is released.
FilenotfoundException for when an input is incorrect either due to wrong filename or non-existent file. In this case an error is thrown and the runnable is not initiated.
Finally, a totallock is initialized to keep tab of the total word count; locking between each document and returning the current wordcount. As a file is finished being read and scanned, the counterlock is locked and threads are reduced until the number of threads reach 0.
It is once the threads have reached zero that total wordcount lock is released and total word count for all files is outputted.
