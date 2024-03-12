# OpierTestLibraryService
Java - 21
SpringBoot - 3.0.7

Please note that due to time limitation i couldnt cover all the unit testings

I used H2 database store all the given datas and programtically inserted all the data as i wanted to convert the dates compatiable with h2


a) returns all users who have actually borrowed at least one book - http://localhost:8383/api/v1/users/borrowed-book

b) returns all non-terminated users who have not currently borrowed anything - http://localhost:8383/api/v1/users/non-terminated

c) returns all users who have borrowed a book on a given date - http://localhost:8383/api/v1/users/borrowed-book?date=05/14/2008

d) returns all books borrowed by a given user in a given date range - http://localhost:8383/api/v1/books/borrowed?user=Chish,Elijah&borrowedFrom=05/14/2008&borrowedTo=05/29/2005

e) returns all available (not borrowed) books - http://localhost:8383/api/v1/books/available

