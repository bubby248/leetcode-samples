def longest_substring_without_repeating(s):
    seen = set()
    p = 0
    max_length = 0

    for q in range(len(s)):
        while s[q] in seen:
            seen.remove(s[p])
            p += 1

        seen.add(s[q])
        max_length = max(max_length, q - p + 1)

    return max_length


print(longest_substring_without_repeating("abcabcbb"))  # 3
print(longest_substring_without_repeating("bbbbb"))     # 1
print(longest_substring_without_repeating("pwwkew"))    # 3