(define (domain MarsDomain)
(:requirements :strips)
(:predicates
    (comsat ?c)
    (rover ?r)
    (location ?l)
    (planet ?p)
    (signal ?s)
    (sendSample ?r ?c)
    (mControl ?mc)
    (soilSample ?ss)
    
    (Empty)
    (hasSignal ?r ?s)
    (comsatFacing ?c ?p)
    (at ?x ?l)
    (in ?x ?r)
    (on ?x ?p)

    )
(:action Move
    :parameters (?r ?l1 ?l2 ?p)
    :precondition 
        (and 
            (rover ?r)
            (location ?l1)
            (location ?l2)
            (at ?r ?l1)
	        (on ?r ?p)
	        (on ?l1 ?p)
	        (on ?l2 ?p)
        )
    :effect 
    (and
        (at ?r ?l2)
        (not (at ?r ?l1))
    )
)
(:action Collect
    :parameters (?r ?ss ?l ?s)
    :precondition
    (and
        (rover ?r)
        (Empty)
        (soilSample ?ss)
        (location ?l)
        (at ?r ?l)
        (at ?ss ?l)
        (hasSignal ?r ?s)
    )
    :effect
    (and
        (in ?ss ?r)
        (not (at ?ss ?l))
        (not (Empty))
        (not(hasSignal ?r ?s))
        
    )

)
(:action TurnComsat
    :parameters (?c ?p1 ?p2)
    :precondition 
        (and 
            (comsatFacing ?c ?p1)
	    (not(comsatFacing ?c ?p2))
        )
    :effect 
    (and
            (comsatFacing ?c ?p2)
	    (not(comsatFacing ?c ?p1))
    )
)
(:action RecieveSignal
    :parameters (?r ?c ?p ?s)
    :precondition
    (and
        (rover ?r)
	    (comsat ?c)
	    (planet ?p)
	    (on ?r ?p)
	    (comsatFacing ?c ?p)
	
    )
    :effect
    (and
        (hasSignal ?r ?s)
        
    )


)
(:action SendSample
    :parameters (?r ?c ?ss ?p)
    :precondition
    (and
        (rover ?r)
	(comsat ?c)
	(planet ?p)
	(soilSample ?ss)
	(on ?r ?p)
	(in ?ss ?r)
	(comsatFacing ?c ?p)
	
    )
    :effect
    (and
        (not(in ?ss ?r))
	(in ?ss ?c)
        (Empty)
    )


)
(:action SendSampleMControl
    :parameters (?mc ?c ?ss ?p)
    :precondition
    (and
	(comsat ?c)
	(planet ?p)
        (mControl ?mc)
	(soilSample ?ss)
	(in ?ss ?c)
	(on ?mc ?p)
	(comsatFacing ?c ?p)
	
    )
    :effect
    (and
        (not(in ?ss ?c))
	(in ?ss ?mc)
    )


)
)
