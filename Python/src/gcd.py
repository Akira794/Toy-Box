# -*- coding: utf-8 -*-

a,b = input(), input()

def gcd(a, b):
	if b == 0:
		return a
	else:
		return gcd(b, a%b)

print  '最大公約数=>', gcd(a,b)

print  '最小公倍数=>', a*b/gcd(a,b)

# print 'word',gcd(a,b)これで説明と数字が表示
