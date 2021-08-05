# battle-bots

A battle bots simulation engine

When I was a boy, I played man games on my Franklin 1000 (a 6502 clone).  One of the
games I played that I remember pitted robots against one another in a square arena.
These bots had different AIs (controllers) and they could move around, turn, and
shoot at each other.

My goal here is to create an arena that begins to recapture some of that mechanic.
In my end vision, I would actually like to replace "AIs" with external controllers
that can be integrated using web protocols.  As such, the AIs become nothing more
than participants in the game.  They see the same inputs as any player.  And therefore,
could be written in any language of choice.

## Approach

The server is a spring-boot server.  Spring boot offers a mechanism for rapidly getting
a server up that can host our simulation.  It provides dependency injection and many
other features that make setting the structure up simple.

## Dependencies

* spring-boot
* stomp - this is a web-socket protocol that allows for general purpose communication 
* dyn4j - this is a physics library that we use for geometries and collision detection

## Building

The application comes bundled with a gradle build process.

* `gradle`

## Testing

All components should be passed through a test suite based on junit.
