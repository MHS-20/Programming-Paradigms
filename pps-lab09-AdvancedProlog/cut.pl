% dropAll (Elem, List, OutList)
% Drop all occurences of the element
dropAll(E, [], []).
dropAll(E, [E | T], T).
dropAll(E, [H | T], [H | L]) :- dropAll(E, T, L).

% dropFirst (Elem, List, OutList)
% Drops the first occurence of the element
dropFirst(E, [], []).
dropFirst(E, [E | T], T) :- !.
dropFirst(E, [H | T], [H | L]) :- dropFirst(E, T, L).

% dropLast (Elem, List, OutList)
% Drops the last occurence of the element
dropLast(E, [], []).
dropLast(E, [E | T], L) :- \+ member(E, T), L = T, !.
dropLast(E, [H | T], [H | L]) :- dropLast(E, T, L).