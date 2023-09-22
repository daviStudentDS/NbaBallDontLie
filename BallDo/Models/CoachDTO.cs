using System.ComponentModel.DataAnnotations;

namespace BallDo.Models
{
    public class CoachDTO
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int ExperienceYears { get; set; }
        public int? TeamId { get; set; }
    }

}
