(define (problem parcel_problem)
  (:domain parcel_delivery)

  (:objects
    lyell
    earl_mountbatten
    david_brewster
    robot
    parcel1
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
    (location robot earl_mountbatten)

    (parcel parcel1)

    (location parcel1 david_brewster)

    (raining)

    (umbrella umbrella1)
    (location umbrella1 earl_mountbatten)

    (door earl_mountbatten david_brewster)
    (door david_brewster earl_mountbatten)
  )

  (:goal
    (and
      (not (wet robot))
      (holding_umbrella)
      (location robot david_brewster)
      (location parcel1 lyell)
    )
  )
)
