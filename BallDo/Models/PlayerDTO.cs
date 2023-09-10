namespace BallDo.Models
{
    public class PlayerDTO
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Position { get; set; }
        public int Age { get; set; }
        public int GoalsScored { get; set; }
        public TeamDTO Team { get; set; }
    }

}
