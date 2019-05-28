import java.io.*;
import java.util.*;
import java.util.concurrent.locks.*;
public class counting  implements Runnable //creates class that implements thread use for counting words in file
{

	private String fileIn;
	public static int total = 0;
	public static int threads = 0;
	private Lock counterLock = new ReentrantLock();;
	private Lock totalLock = new ReentrantLock();;

    public counting(String filename)
    {
    	fileIn = filename;
    }

    public void run()
    {
    	counterLock.lock(); //locks to update number of threads for use in returning total once it reaches 0
    	threads++;
    	counterLock.unlock();
    	int count = 0;
    	try //attempt to execute if inputs are valid throws filenotfoundexception of IOException
    	{
    		Scanner in = new Scanner(new File(fileIn));
    		try //throws exception for empty text file
    		{
    			while (in.hasNext()) //loop until no more elements
    			{    			
	    			in.next();
    				count++;
    			}
    		}
    		catch (NoSuchElementException f) //catch if the textfile is empty
    		{
    			System.out.println("The text file " + fileIn + " is empty");
    		}
    		finally
    		{
    			in.close();
    		}
    	}
    	catch (FileNotFoundException e) //catch exception
    	{
    		System.out.println("File not found: " + fileIn);
    	}
    		System.out.println(fileIn + ": " + count); //returns the count for the document
    		totalLock.lock(); //locks the total so other threads don't conflict
    		total = total + count; //updates the total
    		counterLock.lock(); //lock again to decrease number of threads and check if total should be returned
    		threads--;
    		if(threads==0) //if the thread is the final thread then @return the combined total
    		{
    			System.out.println("combined count: " + total);
    		}
    		counterLock.unlock(); //remove locks for other threads to use
    		totalLock.unlock(); //remove locks for other threads to use
    }
}
