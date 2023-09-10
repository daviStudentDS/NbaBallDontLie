namespace BallDo.Models
{
    public class TeamDTO
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public List<PlayerDTO> Players { get; set; }
        public CoachDTO Coach { get; set; }
    }

}
