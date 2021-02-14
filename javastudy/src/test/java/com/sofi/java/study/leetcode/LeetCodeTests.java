package com.sofi.java.study.leetcode;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;

import com.sofi.java.study.models.ListNode;

public class LeetCodeTests {
	
	@Test
	public void testMe() {
		ListNode l1 = new ListNode(2);
		l1.next = new ListNode(4);
		l1.next.next = new ListNode(9);
		
		ListNode l2 = new ListNode(5);
		l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);
		l2.next.next.next = new ListNode(9);
		
		addTwoNumbers2(l1, l2);
	}
	
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		
		ListNode result = new ListNode();
		ListNode ptr = result;
		
		int carryOver = 0;
		
		while (l1 != null || l2 != null) {
			int lnum = (l1 != null) ? l1.val : 0;
			int snum = (l2 != null) ? l2.val : 0;
			
			int sum = lnum + snum + carryOver;
			
			if (sum > 9) {
				sum = sum - 10;
				carryOver = 1;
			} else {
				carryOver = 0;
			}
			
			ptr.val = sum;
			
			if ((l1 != null && l1.next != null) || (l2 != null && l2.next != null)) {
				ptr.next = new ListNode();
				ptr = ptr.next;
			}
			
			if ((l1 != null)) l1 = l1.next;
			if ((l2 != null)) l2 = l2.next;	
			
		}
		
		if (carryOver > 0) {
			ptr.next = new ListNode(carryOver);
		}
		
		
		return result;
    }

}
