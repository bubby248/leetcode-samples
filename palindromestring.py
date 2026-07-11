def is_palindrome(s):
    p = 0
    q = len(s) - 1
    while p < q:
        if s[p] != s[q]:
            return False
        p = p+1
        q = q-1
    return True

    print(is_palindrome("madam"))  # True
    print(is_palindrome("hello"))  # False