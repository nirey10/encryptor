package encryptor;

public class Timer {

	long startTime;
	long totalTime;
	int state=0; //0 stop, 1 running
	
	public void timer()
	{
		startTime=0;
		totalTime=0;
	}
	public void startTimer()
	{
		startTime = System.nanoTime();
		state=1;
	}
	public void stopTimer()
	{
		if (state==1)
		{
			totalTime += System.nanoTime()-startTime;
			state=0;
		}
	}
	public long getTime()
	{
		return totalTime;
	}
	public void printTime()
	{
		System.out.println("Action ended within "+totalTime + " nano seconds");
	}
	
	
	
}
