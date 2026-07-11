def two_sum(nums, target):
    dict = {}

    for i, num in enumerate(nums):
        needed = target - num

        if needed in dict:
            return [dict[needed], i]

        dict[num] = i

    return []


print(two_sum([2, 7, 11, 15], 9))  # [0, 1]