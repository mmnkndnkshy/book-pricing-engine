# **Book Pricing Engine**

A Test-Driven Development (TDD) based implementation of a pricing engine that calculates the optimal price for a basket of books by applying discount rules.

## **Problem Statement**

This kata models a real-world pricing scenario where discounts are applied based on purchasing different books as a set.

An editor offers discounts to encourage developers to buy multiple distinct books from a popular software development series.

### **Available Books**

1. Clean Code — Robert C. Martin (2008)
2. The Clean Coder — Robert C. Martin (2011)
3. Clean Architecture — Robert C. Martin (2017)
4. Test Driven Development by Example — Kent Beck (2003)
5. Working Effectively with Legacy Code — Michael C. Feathers (2004)

#### **Pricing & Discount Rules**

1.	One copy of the five books costs 50 EUR.
2.	If, however, you buy two different books from the series, you get a 5% discount on those two books.
3.	If you buy 3 different books, you get a 10% discount.
4.	With 4 different books, you get a 20% discount.
5.	If you go for the whole hog, and buy all 5, you get a huge 25% discount.
6.	Note that if you buy, say, 4 books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the 4th book still costs 50 EUR.

###### **Example Scenario**

how much does this basket of books cost?
1.	2 copies of the “Clean Code” book
2.	2 copies of the “Clean Coder” book
3.	2 copies of the “Clean Architecture” book
4.	1 copy of the “Test Driven Development by Example” book
5.	1 copy of the “Working effectively with Legacy Code” book
      
**Answer:**

(4 * 50 EUR) - 20% [first book, second book, third book, fourth book]
(4 * 50 EUR) - 20% [first book, second book, third book, fifth book]
= 160 EUR + 160 EUR = 320 EUR (knowledge is priceless but has a cost)