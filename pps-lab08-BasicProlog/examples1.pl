member([H|T], H, T).
member([H|T], E, [H|T2]):- member(T, E, T2).

permutation([], []).
permutation(L, [H | TP]) :- 
    member(L, H, T), 
    permutation(T, TP).

a.           % a clause with empty body is called a "fact"
b.           % multiple copies of rules/facts can occur
b.
b :- z.      % b is the rule "head", z is the "body"
c.           % different clauses can have same "head"
c :- a, c.   % a sort of recursive rule
c :- b.
d :- d.      % .. recall the order of clauses is relevant

a(1).
a(X) :- b(X), b(X).
b(1).
b(2).
c(X) :- b(X).
c(X) :- b(X), c(X).

father_abraham_isaac.
father_terach_abraham.
grandfather_terach_isaac :- father_abraham_isaac, father_terach_abraham.

father(abraham, isaac).
father(terach, abraham).
grandfather(GF, GS) :- father(GF, F), father(F, GS).

pred(cons(H, nil), H).  % cons/nil as functors
pred(cons(H, T), L) :- pred(T, L). % what does pred define?

odd(1).         % 1 is odd
odd(3).         % 3 is odd
sum(2, 3, 5).   % 2,3,5 are in the sum relation

b_not(b_true, b_false).
b_not(b_false, b_true).
b_and(B, b_true, B).
b_and(B, b_false, b_false).
b_or(B1, B2, B) :-          % (a or b) = !(!a and !b)
    b_not(B1, NB1), b_not(B2, NB2),  b_and(NB1, NB2, NB), b_not(NB, B).
b_implies(B1, B2, B) :-     % a --> b = !a or b
    b_not(B1, NB1), b_or(NB1, B2, B).

% Peano Numbers
succ(X, s(X)).
sum(X, zero, X).
sum(X, s(Y), s(Z)) :- sum(X, Y, Z).

mul(X, zero, zero).
mul(X, s(Y), Z) :- mul(X, Y, W), sum(W, X, Z).

dec(s(X), X).
factorial(zero, s(zero)).
factorial(s(X), Y):-factorial(X, Z), mul(s(X), Z, Y).
greater(s(_), zero).
greater(s(N), s(M)) :- greater(N, M).
range(N1, N2, N1).
range(N1, N2, N) :- greater(N2, N1), range(s(N1), N2, N).
nextprev(s(N), N, s(s(N))).

% relates a list with one of its elements
find(cons(E, _), E).
find(cons(_, T),E) :- find(T, E).