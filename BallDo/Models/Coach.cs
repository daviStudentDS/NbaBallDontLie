using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace BallDo.Models
{
    public class Coach
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int ExperienceYears { get; set; }

        public int? TeamId { get; set; }
        public Team Team { get; set; }
    }
}
