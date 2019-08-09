(define (problem parcel_problem)
  (:domain parcel_delivery)

  (:objects
    lyell
    earl_mountbatten
    david_brewster
    robot
    parcel1
    parcel2
    parcel3
    umbrella1
  )

  (:init
    (building lyell)
    (building earl_mountbatten)
    (building david_brewster)

    (corridor david_brewster earl_mountbatten)
    (corridor earl_mountbatten david_brewster)

    (outside david_brewster lyell)
    (outside lyell david_brewster)

    (outside lyell earl_mountbatten)
    (outside earl_mountbatten lyell)

    (door earl_mountbatten david_brewster)
    (door david_brewster earl_mountbatten)

    (robot robot)
    (location robot david_brewster)

    (parcel parcel1)
    (location parcel1 lyell)

    (parcel parcel2)
    (location parcel2 david_brewster)

    (parcel parcel3)
    (location parcel3 earl_mountbatten)

    (not (wet robot))
    (not (raining))

    (umbrella umbrella1)
    (location umbrella1 earl_mountbatten)
  )

  (:goal
    (and
      (location parcel1 earl_mountbatten)
      (location parcel3 lyell)
      (door_open)
      (not (holding_umbrella))
      (not (wet robot))
      (location robot david_brewster)
    )
  )
)
