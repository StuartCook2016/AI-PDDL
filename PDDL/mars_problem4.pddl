(define (problem MarsProblem)
(:domain MarsDomain)
(:objects
    comsat
    rover1 rover2 rover3 rover4
    site1 site2 site3 site4 site5 site6 site7 site8
    earth mars
    mControl
    sample1 sample2 sample3 sample4 sample5 sample6 sample7 sample8
    signal 
)
(:init
    
    (signal signal)
    (comsat comsat)
    (rover rover1)
    (rover rover2)
    (rover rover3)
    (rover rover4)
    (location site1)
    (location site2)
    (location site3)
    (location site4)
    (location site5)
    (location site6)
    (location site7)
    (location site8)
    (planet earth)
    (planet mars)
    (mControl mControl)
    (soilSample sample1)
    (soilSample sample2)
    (soilSample sample3)
    (soilSample sample4)
    (soilSample sample5)
    (soilSample sample6)
    (soilSample sample7)
    (soilSample sample8)
    (comsatFacing comsat earth)

    (at rover1 site1)
    (at rover2 site3)
    (at rover3 site5)
    (at rover4 site8)
    (at sample1 site1)
    (at sample2 site2)
    (at sample3 site3)
    (at sample4 site4)
    (at sample5 site5)
    (at sample6 site6)
    (at sample7 site7)
    (at sample8 site8)
    (on rover1 mars)
    (on rover2 mars)
    (on rover3 mars)
    (on rover4 mars)
    (on site1 mars)
    (on site2 mars)
    (on site3 mars)
    (on site4 mars)
    (on site5 mars)
    (on site6 mars)
    (on site7 mars)
    (on site8 mars)
    (on mControl earth)
    (Empty)
)
(:goal
    (and
        (at rover1 site2)
        (at rover2 site1)
	(at rover3 site5)
	(at rover4 site8)
        (in sample1 mControl)
        (in sample2 mControl)
        (in sample3 mControl)
        (in sample4 rover4)
        (in sample5 mControl)
	(in sample6 mControl)
	(in sample7 mControl)
	(in sample8 mControl)

    )
)






)
