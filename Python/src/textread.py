# -*- coding: utf-8 -*-

a = open('text.txt').read().split()
a.sort()
for i in a[::-1]:  print i

#別解
#print sorted(open('text.txt').read().split(), key = int, reverse = True)
#sorted関数を使うと、ソートのための一時的なリストを作る必要がなくなるので便利
#


