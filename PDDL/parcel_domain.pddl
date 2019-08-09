(define (domain parcel_delivery)
  (:requirements
    :strips
  )

  (:predicates
    (building ?b)
    (robot ?r)
    (outside ?b1 ?b2)
    (corridor ?b1 ?b2)
    (location ?p ?b)
    (parcel ?p)
    (holding ?p)
    (wet ?r)
    (raining)
    (holding_umbrella)
    (umbrella ?u)
    (door ?b1 ?b2)
    (door_open)
    (holding_door ?p)
  )

  (:action open_door
    :parameters
      (?r ?b1 ?b2 ?p)
    :precondition
      (and
        (robot ?r)
        (building ?b1)
        (building ?b2)
        (door ?b1 ?b2)
        (location ?r ?b1)
        (parcel ?p)
        (holding ?p)
        (not (door_open))
      )
    :effect
      (and
        (door_open)
        (not (holding ?p))
        (holding_door ?p)
      )
  )

  (:action close_door
    :parameters
      (?r ?b1 ?b2 ?p)
    :precondition
      (and
        (robot ?r)
        (building ?b1)
        (building ?b2)
        (door ?b1 ?b2)
        (location ?r ?b1)
        (parcel ?p)
        (holding_door ?p)
        (door_open)
      )
    :effect
      (and
        (not (door_open))
        (holding ?p)
        (not (holding_door ?p))
      )
  )

  (:action move_inside
    :parameters
      (?r ?b1 ?b2)
    :precondition
      (and
        (robot ?r)
        (building ?b1)
        (building ?b2)
        (corridor ?b1 ?b2)
        (location ?r ?b1)
        (door_open)
      )
    :effect
      (and
        (location ?r ?b2)
        (not (location ?r ?b1))
      )
  )

  (:action move_outside
    :parameters
      (?r ?b1 ?b2)
    :precondition
      (and
        (robot ?r)
        (building ?b1)
        (building ?b2)
        (outside ?b1 ?b2)
        (location ?r ?b1)
      )
    :effect
      (and
        (location ?r ?b2)
        (not (location ?r ?b1))
        (when
          (and
            (raining)
            (not (holding_umbrella))
          )
          (and
            (wet ?r)
          )
        )
      )
  )

  (:action pickup_parcel
    :parameters
      (?r ?p ?b)
    :precondition
      (and
        (robot ?r)
        (parcel ?p)
        (building ?b)
        (not (holding ?p))
        (location ?r ?b)
        (location ?p ?b)
      )
    :effect
      (and
        (holding ?p)
        (not (location ?p ?b))
      )
  )

  (:action drop_off_parcel
    :parameters
      (?r ?p ?b)
    :precondition
      (and
        (robot ?r)
        (parcel ?p)
        (building ?b)
        (holding ?p)
        (location ?r ?b)
      )
    :effect
      (and
        (not (holding ?p))
        (location ?p ?b)
      )
  )

  (:action pickup_umbrella
    :parameters
      (?r ?u ?b)
    :precondition
      (and
        (robot ?r)
        (umbrella ?u)
        (building ?b)
        (not (holding_umbrella))
        (location ?u ?b)
        (location ?r ?b)
      )
    :effect
      (and
        (holding_umbrella)
        (not (location ?u ?b))
      )
  )

  (:action drop_off_umbrella
    :parameters
      (?r ?u ?b)
    :precondition
      (and
        (robot ?r)
        (umbrella ?u)
        (building ?b)
        (holding_umbrella)
        (location ?r ?b)
      )
    :effect
      (and
        (not (holding_umbrella))
        (location ?u ?b)
      )
  )
)
