package com.myself.cashBook;

import com.myself.cashBook.utils.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class CashBookApplicationTests {

	@Test
	void contextLoads() {
		System.out.println(">>>>");
		System.out.println(DateUtil.date2TimeStamp("2020-01-01", "yyyy-MM-dd"));
		System.out.println(DateUtil.timeStamp2Date("1577808000", "yyyy-MM-dd"));
	}

}
