def binary_search(arr, target):
    p = 0                 # left/start index
    q = len(arr) - 1      # right/end index

    while p <= q:
        r = (p + q) // 2  # middle index

        if arr[r] == target:
            return r

        elif arr[r] < target:
            p = r + 1     # search right side

        else:
            q = r - 1     # search left side

    return -1


arr = [1, 3, 5, 7, 9, 11, 15]
target = 9

result = binary_search(arr, target)

if result != -1:
    print("Found at index:", result)
else:
    print("Not found")