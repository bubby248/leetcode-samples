a=[1,5,10,1,9]
incl = 0
excl = 0
for k in a:
    temp = incl
    incl = max(excl+k,incl)
    excl = temp
print(incl)