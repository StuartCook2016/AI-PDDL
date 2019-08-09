(define (problem MarsProblem)
(:domain MarsDomain)
(:objects
    comsat
    rover1 rover2
    site1 site2 site3
    earth mars
    mControl
    sample1 sample2 sample3
    signal
)
(:init
    
    (signal signal)
    (comsat comsat)
    (rover rover1)
    (rover rover2)
    (location site1)
    (location site2)
    (location site3)
    (planet earth)
    (planet mars)
    (mControl mControl)
    (soilSample sample1)
    (soilSample sample2)
    (soilSample sample3)
    (comsatFacing comsat mars)

    (at rover1 site1)
    (at rover2 site2)
    (at sample1 site1)
    (at sample2 site2)
    (at sample3 site3)
    (on rover1 mars)
    (on rover2 mars)
    (on site1 mars)
    (on site2 mars)
    (on site3 mars)
    (on mControl earth)
    (Empty)
)
(:goal
    (and
        (at rover1 site2)
        (at rover2 site1)
        (in sample1 mControl)
        (in sample2 mControl)
        (in sample3 mControl)
        
    )
)






)
