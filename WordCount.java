public class WordCount
{
	 	public static void main(String[] args) throws InterruptedException //execute program to count words in a file
 		{
    		if(args.length == 0) //checks for no arguments @return the instructions
    		{
    			System.out.println("You must input the command as: java WordCount aFile where aFile is the file you are counting words for.");
    		}
    		for (String filename : args)
			{
        	Runnable counter = new counting(filename);
	    	Thread t = new Thread(counter);
    		t.start();
      		}

  	 	}
}