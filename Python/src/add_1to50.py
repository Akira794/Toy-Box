#normal

s = 0
for x in range(1,51):
    s += x
print s

#use sum
print sum(range(1,51))

#use generator
print sum(x for x in range(1,51))

#use reduce
print reduce(lambda x,y: x+y, range(1,51))


