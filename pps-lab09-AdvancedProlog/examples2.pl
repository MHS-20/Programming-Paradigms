% sum(List,Sum)
% relate a List of numbers with the sum of its elements
sum([], 0).
sum([H|T], N) :-  sum(T, N2), N is H + N2.

% sum(List,Sum)
% relate a List of numbers with the sum of its elements
sum(L, S) :- sum(L, 0, S).
sum([], S, S).
sum([H|T], N, S) :- N2 is H + N, sum(T, N2, S).

% join(List1, List2, List)
% relate List1 and List2 with their concatenation
join([], L, L).
join([H | T], L, [H | T2]) :- join(T, L, T2).

% update(List1, E1, E2, List2)
% relate List1 with a List2 where first occurrence of E1 is updated with E2
update([], _, _, []).
update([E1 | T], E1, E2, [E2 | T]).
update([H1 | T1], E1, E2, [H1 | T2]) :- update(T1, E1, E2, T2).


% member2(List, Elem, ListWithoutElem)
member2([X | Xs], X, Xs).
member2([X | Xs], E, [X|Ys]) :- member2(Xs, E, Ys).

% permutation(Ilist, Olist)
permutation([], []).
permutation(Xs, [X | Ys]) :- member2(Xs, X, Zs), permutation(Zs, Ys).
	
interval(A, B, A).
interval(A, B, X):- A2 is A+1, A2 < B, interval(A2, B, X).

neighbour(A, B, A, B2):- B2 is B+1.
neighbour(A, B, A, B2):- B2 is B-1.
neighbour(A, B, A2, B):- A2 is A+1.
neighbour(A, B, A2, B):- A2 is A-1.

gridlink(N, M, link(X, Y, X2, Y2)):-
	interval(0, N, X),
	interval(0, M, Y),
	neighbour(X, Y, X2, Y2),
	X2 >= 0, Y2 >= 0, X2 < N, Y2 < M.
	
% merge(+List1,+List2,-OutList)
% merge two sorted lists
merge(Xs, [], Xs) :- !.
merge([], Ys Ys).
merge([X|Xs], [Y|Ys], [X|Zs]) :- X<Y, !, merge(Xs, [Y|Ys], Zs).
merge([X|Xs], [Y|Ys], [Y|Zs]) :- merge([X|Xs], Ys, Zs).

first_index_of([E|_], E, 0) :- !.
first_index_of([_|T], E, N) :- first_index_of(T, E, N2), N is N2 + 1.

index_of([E|_], E, 0).
index_of([_|T], E, N) :- index_of(T, E, N2), N is N2 + 1.
first_index_of2(L, E, N) :- index_of(L, E, N), !.

% quicksort(Ilist,Olist)
quicksort([], []).
quicksort([X | Xs], Ys):-
	partition(Xs, X, Ls, Bs),
	quicksort(Ls, LOs),
	quicksort(Bs, BOs),
	append(LOs, [X | BOs], Ys).

% partition (Ilist,Pivot,Littles,Bigs)
partition([] , _, [], []).
partition([X | Xs], Y, [X | Ls], Bs):- X<Y, !, partition(Xs, Y, Ls, Bs).
partition([X | Xs], Y, Ls, [X | Bs]):- partition(Xs, Y, Ls, Bs).


% all(+Term,+List): are all elements in List of kind Term?
all(_, []).
all(X, [Y | T]):- copy_term(X, Y), all(X, T).

% fully-relational size
size(L, N) :- var(L), !, generate(L, N).
size(L, N) :- length(L, N).

generate([], 0) :- !.
generate([_|T], N) :- N2 is N-1, generate(T,N2).

length([], 0).
length([_|T], N) :- length(T, N2), N is N2 + 1.

% get_ids(+Table,-List)
% gets the List of ids from the Table
get_ids([], []).
get_ids([user(ID, _, _) | T], [ID | L]) :- get_ids(T, L).

% query(+Table,+Id,-Tuple)
% gets the Tuple with Id from the Table
query([user(ID, N, C) | _], ID, user(ID, N, C)).
query([_ | T], ID, Tuple):- query(T, ID, Tuple).

% update(+Table,+Id,+NewTuple,-NewTable)
% updates the tuple with Id to Tuple
update([user(ID, _, _) | T], ID, Tuple, [Tuple | T]).
update([H | T], ID, Tuple, [H | Table]):-update(T, ID, Tuple, Table).

% search(+Tree, + Elem), relates a tree with any of its elements
search(tree(_, E, _), E).
search(tree(L, _, _), E) :- search(L, E).
search(tree(_, _, R), E) :- search(R, E).

% leaves(+Tree,-ListLeaves), returns the list of leaves
leaves(nil, []).			% handling void tree
leaves(tree(nil, E, nil), [E]):- !.	% handling a leaf
leaves(tree(L, _, R), O) :-	% general case
	leaves(L, OL), 		% OL are leaves on left
	leaves(R, OR),		% OR are leaves on right
	append(OL, OR, O).	% O appends the two

% leftlist(+Tree,-List), returns the left-most branch as a list
leftlist(nil, []).
leftlist(tree(nil, E, _), [E]) :- !.
leftlist(tree(T, E, _ ), [E | L]) :- leftlist(T, L).

% searchN(+Tree,?Elem), search Elem in Tree
searchN(tree(E,_), E).
searchN(tree(_,L),E):- member(T, L), search(T, E).

% searchV(+Tree,?Elem), search Elem in Tree
searchV(T, E) :- T =.. [tree, E | _].
searchV(T, E) :- T =.. [tree, _ | L], member(T2, L), searchV(T2, E).

	
% factorial(+N,-Out,?Cache)
% cache is a partial list of factorials [1,1,2,6,24|_]
factorial(N, Out, Cache) :- factorial(N, Out, Cache, 0).

factorial(N, Res, [Res|_], N) :- !, nonvar(Res).

factorial(N, Out, [H, V | T], I) :-
	var(V), !, I2 is I + 1, V is H * I2,  
	factorial(N, Out, [V | T], I2).
	
factorial(N, Out, [_ , V | T], I) :-
	I2 is I + 1, factorial(N, Out, [ V | T ], I2).	