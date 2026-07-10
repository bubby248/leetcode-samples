def rotated_binary_search(arr, target):
    p = 0
    q = len(arr) - 1

    while p <= q:
        r = (p + q) // 2

        print("p:", p, "q:", q, "r:", r, "arr[r]:", arr[r])

        if arr[r] == target:
            return r

        if arr[p] <= arr[r]:
            if arr[p] <= target < arr[r]:
                q = r - 1
            else:
                p = r + 1
        else:
            if arr[r] < target <= arr[q]:
                p = r + 1
            else:
                q = r - 1

    return -1


arr = [4, 5, 6, 7, 0, 1, 2]
target = 0

print("Found at index:", rotated_binary_search(arr, target))