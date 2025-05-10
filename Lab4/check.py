import numpy as np

A = np.array([
    [6.26, 1.10, 0.98, 1.25],
    [1.10, 4.16, 1.00, 0.16],
    [0.98, 1.00, 5.44, 2.12],
    [1.25, 0.16, 2.12, 6.00]
])

eigenvalues = np.linalg.eig(A)[0]
print("Значення:", sorted(eigenvalues))