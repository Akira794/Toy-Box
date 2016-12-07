# -*- coding: utf-8 -*-

#怠惰な方法
#for i in range(2,100):
#	if 0 not in [i%j for j in range(2,i/2+1)]:
#		print i

#少しちゃんとした方法

#from math import sqrt

#for i in range(2,10000):
#	if 0 not in [i%j for j in range(2, int(sqrt(i))+1)]:
#		print i

#上のよりもっと早い方法
#2に対して2より大きい偶数を削除し3に対して3より大きく3で割れる数を削除し・・・・
#としていくので無駄なチェックが減る

MAX  = 100
LIST = range(2,MAX + 1)
for i in range(2, int(MAX ** 0.5)):
	LIST = [x for x in LIST if (x == i or x%i != 0)]
for j in LIST:
	print j





