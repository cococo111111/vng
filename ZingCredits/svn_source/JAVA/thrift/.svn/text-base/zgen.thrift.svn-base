
namespace cpp ZGenerator
namespace java ZGenerator
namespace php ZGenerator

exception InvalidOperation {
  1: i32 error,
  2: string message
}

service Generator {
	i32 createGenerator(1:string genName)
		throws (1:InvalidOperation ouch),
		
	i32 removeGenerator(1:string genName)
		throws (1:InvalidOperation ouch),
		
	i64 getCurrentValue(1:string genName)
		throws (1:InvalidOperation ouch),
		
	i64 getValue(1:string genName)
		throws (1:InvalidOperation ouch),
}
