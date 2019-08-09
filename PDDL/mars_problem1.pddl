(define (problem MarsProblem)
(:domain MarsDomain)
(:objects
    comsat
    rover
    site1 site2
    earth mars
    mControl
    sample1 sample2
    signal
)
(:init
    
    (signal signal)
    (comsat comsat)
    (rover rover)
    (location site1)
    (location site2)
    (planet earth)
    (planet mars)
    (mControl mControl)
    (soilSample sample1)
    (soilSample sample2)
    (comsatFacing comsat earth)

    (at rover site1)
    (at sample2 site2)
    (on rover mars)
    (on site1 mars)
    (on site2 mars)
    (on mControl earth)
    (Empty)
)
(:goal
    (and
        (at rover site2)
        (in sample2 mControl)
        
    )
)






)
