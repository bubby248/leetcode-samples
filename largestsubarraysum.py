a = [-2, 3, 4]

max_so_far = float("-inf")
max_ending_here = 0

for i in range(len(a)):
    max_ending_here = max_ending_here + a[i]

    if max_so_far < max_ending_here:
        max_so_far = max_ending_here

    if max_ending_here < 0:
        max_ending_here = 0

print(max_so_far)