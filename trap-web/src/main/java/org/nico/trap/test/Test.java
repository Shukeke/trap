package org.nico.trap.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Test {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		String info = "$[start] 看到了一条狗\r\n" + 
				"	@ 向它丢一个包子\r\n" + 
				"		$ 狗狗来到你的脚边并用头蹭了蹭你的腿，蹭蹭蹭...蹭蹭蹭...蹭蹭蹭...蹭蹭..蹭蹭蹭...蹭蹭蹭...\r\n" + 
				"			@ 蹲下来摸摸它的头\r\n" + 
				"				> end_one\r\n" + 
				"			@ 抬脚离开\r\n" + 
				"				> start\r\n" + 
				"	@ 向它丢一个石子\r\n" + 
				"		> end_three\r\n" + 
				"	\r\n" + 
				"$[end_one] 你真是一个和蔼的人\r\n" + 
				"$[end_two] 你真是一个善良的人\r\n" + 
				"$[end_three] 狗狗吓得跑开了";
		
		String[] exec = new String[] {"python", "D:\\workspace-github\\trap\\trap-grammar\\trap.py", "-t", info};
		Process process = Runtime.getRuntime().exec(exec);

		InputStream stream = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
		process.waitFor();
		
		String line = null;
		while((line = reader.readLine()) != null) {
			System.out.println(line);
		}
	}
}
