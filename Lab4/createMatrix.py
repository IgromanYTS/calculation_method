import numpy as np

def danilevsky(A):
    n = A.shape[0]
    A_k = A.copy()
    transformations = []

    for k in range(n - 2, -1, -1):  
        a = A_k[k + 1]
        pivot = a[k]
        if pivot == 0:
            raise ValueError(f"Pivot zero at k={k}, can't continue")

        Mk = np.eye(n)
        Mk[k] = [-a[i] / pivot if i != k else 1 / pivot for i in range(n)]

        Mk_inv = np.eye(n)
        Mk_inv[k] = [a[i] if i != k else pivot for i in range(n)]

        A_k = Mk_inv @ A_k @ Mk

        transformations.append((Mk, Mk_inv))
        print(f"Mk{k} ", np.round(Mk,3))
        print(f"Mk_inv{k} ", np.round(Mk_inv,3))
        print(f"A{k} ", np.round(A_k, 3))

    return A_k, transformations


A = np.array([
    [6.26, 1.10, 0.98, 1.25],
    [1.10, 4.16, 1.00, 0.16],
    [0.98, 1.00, 5.44, 2.12],
    [1.25, 0.16, 2.12, 6.00]
])

Frobenius_form, steps = danilevsky(A)

print("Frobenius form (P1):")
print(np.round(Frobenius_form, 4))