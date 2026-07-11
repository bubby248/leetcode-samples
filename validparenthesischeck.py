def is_valid_parentheses(s):
    stack = []

    pairs = {
        ")": "(",
        "}": "{",
        "]": "["
    }

    for ch in s:
        if ch in "({[":
            stack.append(ch)
        else:
            if not stack:
                return False

            top = stack.pop()

            if top != pairs[ch]:
                return False

    return len(stack) == 0


print(is_valid_parentheses("()"))        # True
print(is_valid_parentheses("()[]{}"))    # True
print(is_valid_parentheses("(]"))        # False
print(is_valid_parentheses("([{}])"))    # True