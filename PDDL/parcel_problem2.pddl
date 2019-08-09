(define (problem parcel_problem)
  (:domain parcel_delivery)

  (:objects
    lyell
    earl_mountbatten
    david_brewster
    robot
    parcel1
    parcel2
    umbrella1
  )

  (:init
    (building lyell)
    (building earl_mountbatten)
    (building david_brewster)

    (outside lyell earl_mountbatten)
    (outside earl_mountbatten lyell)

    (outside lyell david_brewster)
    (outside david_brewster lyell)

    (corridor david_brewster earl_mountbatten)
    (corridor earl_mountbatten david_brewster)

    (robot robot)
    (location robot lyell)

    (parcel parcel1)
    (location parcel1 lyell)

    (parcel parcel2)
    (location parcel2 david_brewster)

    (raining)

    (umbrella umbrella1)
    (location umbrella1 earl_mountbatten)

    (door earl_mountbatten david_brewster)
    (door david_brewster earl_mountbatten)
  )

  (:goal
    (and
      (location parcel1 earl_mountbatten)
      (wet robot)
      (holding_umbrella)
      (door_open)
      (location robot lyell)
    )
  )
)
