#Two strings are anagrams if they contain the same characters with the same frequency.
def is_anagram(s1, s2):
    if len(s1) != len(s2):
        return False

    count = {}

    for ch in s1:
        count[ch] = count.get(ch, 0) + 1

    for ch in s2:
        if ch not in count:
            return False

        count[ch] -= 1

        if count[ch] < 0:
            return False

    return True


print(is_anagram("listen", "silent"))  # True
print(is_anagram("hello", "world"))    # False